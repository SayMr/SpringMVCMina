package com.laolaoke.minademo.minaserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class DataDecoderEx extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		if (in.remaining() < 4) {//当拆包时候剩余长度小于4的时候的保护，不加会出错
			return false;
		}
		if (in.remaining() > 1) {
			in.mark();// 标记当前位置，以便reset
			int length = in.getInt(in.position());
			if (length > in.remaining() - 4) {// 如果消息内容不够，则重置，相当于不读取size
				in.reset();
				return false;// 接收新数据，以拼凑成完整数据
			} else {
				in.getInt();
				byte[] bytes = new byte[length];
				in.get(bytes, 0, length);
				String str = new String(bytes, "UTF-8");
				if (null != str && str.length() > 0) {
					out.write(str);
				}
				return true;// 这里有两种情况1：没数据了，那么就结束当前调用，有数据就再次调用
			}
		}
		return false;// 处理成功，让父类进行接收下个包
	}

}
