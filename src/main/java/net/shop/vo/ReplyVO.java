package net.shop.vo;


public abstract class ReplyVO {

    public String sequenceNumber;

    public abstract int getNumber();
    public abstract String getSequenceNumber();

    public int getLevel(){
        if(sequenceNumber == null)          return -1;
        if(sequenceNumber.length() != 12)   return -1;

        if(sequenceNumber.endsWith("99"))   return 0;
        return 1;
    }

}
