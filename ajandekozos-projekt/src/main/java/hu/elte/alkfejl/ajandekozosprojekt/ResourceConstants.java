package hu.elte.alkfejl.ajandekozosprojekt;

public class ResourceConstants {

    public static final String USER = "/user";
    public static final String USER_LOGIN = "/login";
    public static final String USER_LOGOUT = "/logout";
    public static final String USER_REGISTER = "/register";

    public static final String WISHLISTS = USER + "/wishlists";
    public static final String WISHLISTSID = WISHLISTS + "/{wishlistId}";

    public static final String PRESENTS = WISHLISTSID + "/presents";
    public static final String PRESENTSID = PRESENTS + "/{presentId}";

    public static final String FRIEND_REQUESTS = "/friendrequests";

    public static final String FRIENDS = USER + "/friends";
    public static final String FRIEND_LISTS = FRIENDS + "/{friendId}/wishlists";
    public static final String FRIEND_PRESENTS = FRIEND_LISTS + "/{friendListId}/presents";
    public static final String FRIEND_PRESENTID = FRIEND_PRESENTS + "/{friendPresentId}";

    public static final String COMMENTS = FRIEND_PRESENTID + "/comments";
    public static final String COMMENTID = "/{commentId}";

}
