package hu.elte.alkfejl.ajandekozosprojekt;

public class ResourceConstants {

    public static final String USER = "/user";
    public static final String USER_LOGIN = "/login";
    public static final String USER_REGISTER = "/register";
    public static final String USER_LOGOUT = "/user/logout";

    public static final String WISHLISTS = "/user/wishlists";
    public static final String WISHLISTSID = "/user/wishlists/{wishlistId}";

    public static final String PRESENTS = "/user/wishlists/{wishlistId}/presents";
    public static final String PRESENTSID = "/user/wishlists/{wishlistId}/presents/{presentId}";

    public static final String FRIEND_REQUESTS = "/user/friendrequests";
    public static final String FRIEND_REQUESTID = "/user/friendrequests/{friendRequestId}";

    public static final String SEARCH_USER_OR_CREATE_FRIENDREQUEST = "/user/newrequest";

    public static final String FRIENDS = "/user/friends";
    public static final String FRIEND_LISTS = "/user/friends/{friendId}/wishlists";
    public static final String FRIEND_PRESENTS = "/user/friends/{friendId}/wishlists/{friendListId}/presents";
    public static final String FRIEND_PRESENTID = "/user/friends/{friendId}/wishlists/{friendListId}/presents/{friendPresentId}";

    public static final String COMMENTS = "/user/friends/{friendId}/wishlists/{friendListId}/presents/{friendPresentId}/comments";
    public static final String COMMENTID = "/user/friends/{friendId}/wishlists/{friendListId}/presents/{friendPresentId}/comments/{commentId}";

    public static final String DELETE_FRIEND_OR_USER = "/user/friends/{friendId}";
    public static final String DELETE_OR_UPDATE_USER_LIST = "/user/friends/{friendId}/wishlists/{userListId}";


}
