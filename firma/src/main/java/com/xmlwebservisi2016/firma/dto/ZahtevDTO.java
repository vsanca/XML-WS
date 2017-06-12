package com.xmlwebservisi2016.firma.dto;

import java.sql.Date;

/**
 * Created by Svetozar Stojkovic on 6/12/2017.
 */
public class ZahtevDTO {

    private long fid;
    private Date date;

    public ZahtevDTO() {
    }

    public ZahtevDTO(long fid, Date date) {
        this.fid = fid;
        this.date = date;
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
