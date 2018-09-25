package com.hx.model;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:41
 *@功能:
 */

import java.io.Serializable;
import java.util.Date;

public class Filetoacept implements Serializable {
    private static final long serialVersionUID = -6962999050093380204L;
    private Integer id;
    private Integer dispatcher_id;
    private String filename;
    private String filesaving;
    private Byte readunread;  //1为已读,2为未读
    private Integer receiver_id;
    private Integer del_flag;



    @Override
    public String toString() {
        return "Filetoacept{" +
                "id=" + id +
                ", dispatcher_id=" + dispatcher_id +
                ", filename='" + filename + '\'' +
                ", filesaving='" + filesaving + '\'' +
                ", readunread=" + readunread +
                ", receiver_id=" + receiver_id +
                ", del_flag=" + del_flag +
                '}';
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }





    private String name;
    private String status;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDispatcher_id() {
        return dispatcher_id;
    }

    public void setDispatcher_id(Integer dispatcher_id) {
        this.dispatcher_id = dispatcher_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesaving() {
        return filesaving;
    }

    public void setFilesaving(String filesaving) {
        this.filesaving = filesaving;
    }

    public Byte getReadunread() {
        return readunread;
    }

    public void setReadunread(Byte readunread) {
        this.readunread = readunread;
    }

    public Integer getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Integer receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
