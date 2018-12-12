package consts;

/**
 * @author len
 */

public enum ErrorsEnum {
    /*
     * �޷������������
     */
    CMD_ERROR(0x0001, "�����ִ���");

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
