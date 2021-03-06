package gvsu.chua_hoffmann_strasler.qrcodegames.androidclient.welcome;

import gvsu.chua_hoffmann_strasler.qrcodegames.androidclient.BasePresenter;
import gvsu.chua_hoffmann_strasler.qrcodegames.androidclient.BaseView;

/**
 * The contract binding the Connect View and Presenter using the MVP pattern.
 */
public interface WelcomeContract {
    /**
     * The Connect View interface.
     */
    interface View extends BaseView<Presenter> {


        /**
         * Displays the given username by changing the TextView.
         *
         * @param userName The user's username
         */
        void showUserName(String userName);

        /**
         * Opens activity to create a game.
         */
        void createGame();

        /**
         * Opens activity to join a game.
         */
        void joinGame();

    }

    /**
     * The Connect Presenter interface.
     */
    interface Presenter extends BasePresenter {


        /**
         * Gets the user's username.
         *
         * @return The user's username
         */
        String getUserName();

        /**
         * Sets the given username.
         *
         * @param userName The user's username
         */
        void setUserName(String userName);

        /**
         * Opens Create game activity.
         */
        void createGame();

        /**
         * Opens Join game activity.
         */
        void joinGame();
    }
}
