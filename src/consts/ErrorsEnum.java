package consts;

/**
 * @author len
 */

public enum ErrorsEnum {
    /*
     * ����ɹ�
     */
    PROC_SUCCESS(0x0000, "���׳ɹ�"),
    /*
     * �޷������������
     */
    CMD_ERROR(0x0001, "�����ִ���"),

    /**
     * ��¼�����������
     */
    PASSWORD_ERROR(0x0002, "�������");

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
