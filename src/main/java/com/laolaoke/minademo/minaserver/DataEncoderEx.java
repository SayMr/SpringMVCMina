package com.laolaoke.minademo.minaserver;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class DataEncoderEx extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
		System.out.println(message);
        IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
        String strOut = message.toString();
        buf.putInt(strOut.getBytes(Charset.forName("utf-8")).length);
        buf.putString(strOut, Charset.forName("utf-8").newEncoder());
        buf.flip();
        out.write(buf);
	}

}
