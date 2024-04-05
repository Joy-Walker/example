package com.example.message;



/**
 * @author :panligang
 * @description :
 * @create :2024-04-05 13:55:00
 */
public class MessageHeader {

    private int magicNumber;

    private int messageType;

    private int version;

    private int serializerType;

    private int bodyLength;

    private int length;



    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSerializerType() {
        return serializerType;
    }

    public void setSerializerType(int serializerType) {
        this.serializerType = serializerType;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    @Override
    public String toString() {
        return "MessageHeader{" +
                "magicNumber=" + magicNumber +
                ", messageType=" + messageType +
                ", version=" + version +
                ", serializerType=" + serializerType +
                ", bodyLength=" + bodyLength +
                ", length=" + length +
                '}';
    }

    public static class Builder{

        private int magicNumber;

        private int messageType;

        private int version;

        private int serializerType;

        private int bodyLength;

        private int length;

        public MessageHeader build(){
            MessageHeader messageHeader = new MessageHeader();
            messageHeader.setMessageType(messageType);
            messageHeader.setVersion(version);
            messageHeader.setLength(length);
            messageHeader.setSerializerType(serializerType);
            messageHeader.setBodyLength(bodyLength);
            messageHeader.setMagicNumber(magicNumber);
            return messageHeader;
        }
        public Builder setMessageType(int messageType){
            this.messageType = messageType;
            return this;
        }

        public Builder setVersion(int version){
            this.version = version;
            return this;
        }

        public Builder setLength(int length){
            this.length = length;
            return this;
        }

        public Builder setSerializerType(int serializerType){
            this.serializerType = serializerType;
            return this;
        }

        public Builder setBodyLength(int bodyLength){
            this.bodyLength = bodyLength;
            return this;
        }

        public Builder setMagicNumber(int magicNumber){
            this.magicNumber = magicNumber;
            return this;
        }

        public Builder(){

        }

    }
}
