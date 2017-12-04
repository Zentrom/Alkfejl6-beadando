package hu.elte.alkfejl.ajandekozosprojekt;

public class ResourceConstants {

    public static final String USER = "/user";
    public static final String USER_LOGIN = "user/login";
    public static final String USER_LOGOUT = "user/logout";
    public static final String USER_REGISTER = "user/register";

    public static final String WISHLISTS = USER + "/wishlists";
    public static final String WISHLISTSID = WISHLISTS + "/{wishlistId}";

    public static final String PRESENTS = WISHLISTSID + "/presents";
    public static final String PRESENTSID = PRESENTS + "/{presentId}";

    public static final String FRIEND_REQUESTS = USER + "/friendrequests";
    public static final String FRIEND_REQUESTID = FRIEND_REQUESTS + "/{friendRequestId}";

    public static final String SEARCH_USER_OR_CREATE_FRIENDREQUEST = USER + "/newrequest";

    public static final String FRIENDS = USER + "/friends";
    public static final String FRIEND_LISTS = FRIENDS + "/{friendId}/wishlists";
    public static final String FRIEND_PRESENTS = FRIEND_LISTS + "/{friendListId}/presents";
    public static final String FRIEND_PRESENTID = FRIEND_PRESENTS + "/{friendPresentId}";

    public static final String COMMENTS = FRIEND_PRESENTID + "/comments";
    public static final String COMMENTID = "/{commentId}";

    public static final String DELETE_FRIEND_OR_USER = FRIENDS + "/{friendId}";
    public static final String DELETE_OR_UPDATE_USER_LIST = FRIEND_LISTS + "/{userListId}";


}
