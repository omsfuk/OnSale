package cn.omsfuk.onsale.base;

public class ResultCache {

    public static final Result OK = new Result(200, "ok", null);



    public static final Result INVALID_INPUT = new Result(300, "Invalid input", null);

    public static final Result Unauthorized = new Result(301, "Unauthorized", null);

    public static final Result NO_SUCH_USER = new Result(302, "No such user", null);

    public static final Result WRONG_PASSWORD = new Result(303, "Wrong password", null);

    public static final Result USER_ALREADY_EXISTS = new Result(304, "User already exists", null);




    public static Result getOk(Object data) {
        return new Result(200, "ok", data);
    }

}
