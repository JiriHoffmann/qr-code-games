package gvsu.chua_hoffmann_strasler.qrcodegames.androidclient.create;



/**
 * The presenter for the Connect Activity holding all presentation logic.
 */
public class CreatePresenter implements CreateContract.Presenter {

    /**
     * The Connect View.
     */
    private CreateContract.View mCreateView;

    /**
     * The user's username.
     */
    private String mUserName;

    /**
     * Initializes the connection between the Connect View and this presenter.
     *
     * @param createView The Connect View to be bound to this presenter
     */
    public CreatePresenter(final CreateContract.View createView) {
        mCreateView = createView;
        mCreateView.setPresenter(this);
    }



    /**
     * Checks if the given string is a valid IP address.
     *
     * @param ip The IP address to be checked
     * @return If the given string is a valid IP address
     */
    @Override
    public boolean isValidIPAddress(final String ip) {
        String strPattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return ip.matches(strPattern);
    }

    /**
     * Checks if the given port is a valid port.
     *
     * @param port The integer to be checked
     * @return If the given integer is a valid port
     */
    @Override
    public boolean isValidPort(final String port) {
        return port.matches("\\d+");
    }

    /**
     * Checks if the given game string is a valid game that can be played via
     * this app.
     *
     * @param game The string to be checked
     * @return If the given game is a valid game
     */
    @Override
    public boolean isValidGame(final String game) {
        try {
            boolean isValid = Integer.parseInt(game) == 0;

            return isValid;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    /**
     * Checks if the given inputs are valid, and if so, attempts to connect to
     * the server and create a new game.
     *
     * @param ip IP address
     * @param port Port
     * @param game The type of game to be created
     */
    @Override
    public void createGame(final String ip, final String port,
                           final String game) {
        if (!hasUserNameSet()) {
            mCreateView.showError("Please register your username first");
            return;
        }

        if (!isValidIPAddress(ip)) {
            mCreateView.showError("The provided IP address is not valid");
            return;
        }


        if (!isValidGame(game)) {
            mCreateView.showError("The game option provided is not valid.");
            return;
        }


        if (!isValidPort(port)) {
            mCreateView.showError("The port provided is not valid");
            return;
        }

        mCreateView.sendCreateGameRequest(ip, Integer.parseInt(port),
                mUserName, Integer.parseInt(game));
    }


    /**
     * Sets the given username.
     *
     * @param userName The user's username
     */
    @Override
    public void setUserName(final String userName) {
        mUserName = userName;
    }

    /**
     * Gets the user's username.
     *
     * @return The user's username
     */
    @Override
    public String getUserName() {
        return mUserName;
    }

    /**
     * Checks if there is a username set.
     *
     * @return If there is a username set
     */
    @Override
    public boolean hasUserNameSet() {
        return mUserName != null;
    }


    /**
     * Used to initialize the presenter in testing.
     */
    @Override
    public void start() {

    }

    @Override
    public void getBack() {
        mCreateView.getBack();
    }
}
