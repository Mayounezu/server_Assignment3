package bgu.spl.net.impl.stomp.Frames;

import bgu.spl.net.impl.stomp.Frame;

public class ReceiptFrame extends Frame {


    protected ReceiptFrame(int senderId) {
        super(senderId);
    }

    @Override
    public String processFrame() {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }
}
