package hu.elte.alkfejl.ajandekozosprojekt;

public class ResourceConstants {

    //User
    public static final String USER = "api/user";
    public static final String USER_LOGIN = "api/login";
    public static final String USER_REGISTER = "api/register";
    public static final String USER_LOGOUT = "api/user/logout";

    public static final String WISHLISTS = "api/user/wishlists";
    public static final String WISHLISTSID = "api/user/wishlists/{wishlistId}";

    public static final String PRESENTS = "api/user/wishlists/{wishlistId}/presents";
    public static final String PRESENTSID = "api/user/wishlists/{wishlistId}/presents/{presentId}";

    public static final String FRIEND_REQUESTS = "api/user/friendrequests";
    public static final String FRIEND_REQUESTID = "api/user/friendrequests/{friendRequestId}";

    public static final String SEARCH_USER_OR_CREATE_FRIENDREQUEST = "api/user/newrequest";

    public static final String FRIENDS = "api/user/friends";
    public static final String FRIEND_LISTS = "api/user/friends/{friendId}/wishlists";
    public static final String FRIEND_PRESENTS = "api/user/friends/{friendId}/wishlists/{friendListId}/presents";
    public static final String FRIEND_PRESENTID = "api/user/friends/{friendId}/wishlists/{friendListId}/presents/{friendPresentId}";

    public static final String COMMENTS = "api/user/friends/{friendId}/wishlists/{friendListId}/presents/{friendPresentId}/comments";
    public static final String COMMENTID = "api/user/friends/{friendId}/wishlists/{friendListId}/presents/{friendPresentId}/comments/{commentId}";

    public static final String DELETE_FRIEND = "api/user/friends/{friendId}";

    //Admin
    public static final String USERS = "api/users";
    public static final String USERSID = "api/users/{userId}";


}
