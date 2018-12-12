package consts;

/**
 * @author len
 */

public enum ErrorsEnum {
    /*
     * 无法处理的命令字
     */
    CMD_ERROR(0x0001, "命令字错误");

    private int code;
    private String msg;

    private ErrorsEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
