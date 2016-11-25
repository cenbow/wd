package com.chinapay.util.connection;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class CPHttpConnection {

    /**
     * ��Ϣ����
     */
    private String msgEncoding;

    /**
     * �Է����������ؽ��ճɹ�����Ӧ��
     */
    static final int RESPCODE_SUCCESS = 200;
    /**
     * ������Ӧ�����ֵ
     */
    protected static final int MAXLEN = 4096;

    /**
     * ��������������С
     */
    protected static final int NOLIMITLEN = 0;

    /**
     * ������������СΪ4k
     */
    protected static final int LIMITLEN = 1;

    /**
     * ������Ӧ�ĳ�����������<br>
     * �����Ƴ��ȴ�С,����http�������ݳ��Ȼ��:0<br>
     * ���Ƴ��ȴ�СΪMAXLEN:1<br>
     */
    protected int lenType = LIMITLEN;

    /**
     * ���ձ��ĵĵ�ַ
     */
    protected String URL;

    /**
     * ���Ӻͽ�����Ӧ�ĳ�ʱʱ��
     */
    protected String timeOut;

    /**
     * ���͵ı�������
     */
    String sendData;

    /**
     * ���յı�������
     */
    protected byte[] receiveData;

    /**
     * ������
     */
    protected BufferedInputStream iBufferedInputStream = null;

    /**
     * ���ͱ��ĵ����յ�ַ�����ҽ��շ�����Ϣ
     *
     * @param msgStr ��Ҫ���͵�����
     * @return
     */
    public int sendMsg(String msgStr) {
        HttpURLConnection urlCon;
        OutputStream tOut = null;
        int result = -1;

        //设置超时
        System.setProperty("sun.net.client.defaultConnectTimeout", "" + timeOut);
        System.setProperty("sun.net.client.defaultReadTimeout", "" + timeOut);

        // �������ӣ���������
        try {
            // ����URL����
            URL tUrl = new URL(URL);
            urlCon = (HttpURLConnection) tUrl.openConnection();

        }
        catch (MalformedURLException mue) {
            System.out.println("����ʧ��,errCode=[-12];errMsg=[�޷����ӶԷ�����]");
            //TraceLog.logStackTrace(this, (Throwable) mue);
            return -12;
        }
        catch (IOException ioe) {
            //TraceLog.logStackTrace(this, (Throwable) ioe);
            System.out.println("����ʧ��,errCode=[-14];errMsg=[ͨѶ����ʧ��]");
            return -14;
        }

        try {
        	System.out.println("sendData=[" + msgStr + "]");

            urlCon.setRequestMethod("POST");
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            tOut = urlCon.getOutputStream();

            // �ж��ַ������Ƿ�Ϊ�գ���Ϊ�գ������ñ���
            if (getMsgEncoding() == null || getMsgEncoding().equals(""))
                tOut.write(msgStr.getBytes());
            else
                tOut.write(msgStr.getBytes(getMsgEncoding()));
            tOut.flush();

        }
        catch (IOException ioe) {
            //TraceLog.logStackTrace(this, (Throwable) ioe);
            System.out.println("Http����ʧ��,errCode=[-52];errMsg=[���ݴ���ʱ]");
            return -52;
        }
        catch (Exception e) {
        	System.out.println("���ݴ���ʧ��");
            //TraceLog.logStackTrace(this, (Throwable) e);
            return -1;
        }
        finally {
            try {
                if (tOut != null)
                    tOut.close();
            }
            catch (IOException e) {
            	System.out.println("������ر�ʧ��");
                //TraceLog.logStackTrace(this, (Throwable) e);
                return -1;
            }
        }

        // ���������ķ�ʽ������Ӧ����
        InputStream is = null;
        try {
            int respCode = urlCon.getResponseCode();
            System.out.println("http ResponseCode=[" + respCode + "]");
            if (RESPCODE_SUCCESS == respCode) {
                                int contentLen = urlCon.getContentLength();
                is = urlCon.getInputStream();

                /**
                 * ��ȡ ContentLengthʧ�ܣ������ByteArrayOutputStream��ȡ������
                 */
                System.out.println("ContentLength=[" + contentLen + "]");
                if (contentLen == -1) {
                	System.out.println("��ȡContentLengthʧ�ܣ�ͨ��ByteArrayOutputStream��ʽ��ȡ����");
                    receiveData = getBytes(is, lenType == NOLIMITLEN ? 0 : MAXLEN);
                } else {
                    /**
                     * ��ȡ ContentLength �ɹ�
                     */
                    int len = lenType == NOLIMITLEN ? contentLen : Math.min(contentLen, MAXLEN);
                    System.out.println("ReceiveData Length=[" + len + "]");
                    receiveData = new byte[len];
                    int ch;
                    int i=0;
                    while((ch = is.read())!=-1){
                        receiveData[i++] = (byte) ch;
                        if(i==len) break;
                    }
//                    is.read(receiveData, 0, len);
                }
                result = 1;
            } else {
            	System.out.println(new StringBuffer("�Է����ش������=[").append(
                        respCode).append("];respMsg=[").append(
                        urlCon.getResponseMessage()).append("]").toString());
            }

        }
        catch (IOException ioex) {
            //TraceLog.logStackTrace(this, (Throwable) ioex);
        	System.out.println("����ʧ��,errCode=[-52];errMsg=[���ݽ��մ���]");
            return -54;
        }
        catch (Exception ex) {
        	System.out.println("ϵͳ�����쳣");
            //TraceLog.logStackTrace(this, (Throwable) ex);
            return -1;
        }
        finally {
            try {
                if (is != null)
                    is.close();
            }
            catch (IOException e) {
            	System.out.println("�������ر�ʧ��");
                //TraceLog.logStackTrace(this, (Throwable) e);
                return -1;
            }

        }

        return result;

    }

    /**
     * ��öԷ����صı�������
     *
     * @return
     */
    public abstract byte[] getReceiveData();

    public String getMsgEncoding() {
        return msgEncoding;
    }

    public void setMsgEncoding(String msgEncoding) {
        this.msgEncoding = msgEncoding;
    }

    public int getLenType() {
        return lenType;
    }

    public void setLenType(int lenType) {
        this.lenType = lenType;
    }

    /**
     * ��ȡ������
     *
     * @param is        ������
     * @param limitSize ��ȡ����󳤶ȣ�����������Ϊ0
     * @return ������byte����
     * @throws IOException ����ʧ���׳��쳣
     */
    static public byte[] getBytes(InputStream is, int limitSize) throws IOException {
        if (is == null) {
            throw new IOException("InputStream is Closed!");
        }
        ByteArrayOutputStream os = null;
        try {
            int ch;
            os = new ByteArrayOutputStream();
            int count = 0;
            while ((ch = is.read()) != -1) {
                if (limitSize != 0 && count >= limitSize) break;
                os.write(ch);
                count++;
            }
            os.flush();
            System.out.println("getBytes:Length=[" + count + "]");
            return os.toByteArray();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        } finally {
            if (os != null) os.close();
        }
    }

}
