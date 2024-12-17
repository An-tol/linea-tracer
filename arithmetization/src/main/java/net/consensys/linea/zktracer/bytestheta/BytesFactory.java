package net.consensys.linea.zktracer.bytestheta;

import net.consensys.linea.zktracer.types.UnsignedByte;
import org.apache.tuweni.bytes.Bytes;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class BytesFactory {

  static ConcurrentHashMap<Short, Bytes> cache = new ConcurrentHashMap<>();
  static ConcurrentHashMap<Short, UnsignedByte> unsignedCache = new ConcurrentHashMap<>();

  public static Bytes of(short input){
//    return Bytes.of(input);
    return cache.computeIfAbsent(input, i -> Bytes.of(i));
  }

  public static UnsignedByte unsignedOf(short input) {
    return unsignedCache.computeIfAbsent(input, i->UnsignedByte.of(i));
  }

  private static ConcurrentHashMap<Bytes, ConcurrentHashMap<Bytes, UnsignedByte>> andCache = new ConcurrentHashMap<>();
  public static UnsignedByte and(Bytes input1Bytes2, Bytes input2Bytes2) {

    return andCache.computeIfAbsent(input1Bytes2, i -> new ConcurrentHashMap<>()).computeIfAbsent(
            input2Bytes2, i-> UnsignedByte.of(input1Bytes2.and(input2Bytes2).get(0))
    );
  }

  private static ConcurrentHashMap<Bytes, ConcurrentHashMap<Bytes, UnsignedByte>> orCache = new ConcurrentHashMap<>();
  public static UnsignedByte or(Bytes input1Bytes2, Bytes input2Bytes2) {

    return orCache.computeIfAbsent(input1Bytes2, i -> new ConcurrentHashMap<>()).computeIfAbsent(
            input2Bytes2, i-> UnsignedByte.of(input1Bytes2.or(input2Bytes2).get(0))
    );
  }

  private static ConcurrentHashMap<Bytes, ConcurrentHashMap<Bytes, UnsignedByte>> xorCache = new ConcurrentHashMap<>();
  public static UnsignedByte xor(Bytes input1Bytes2, Bytes input2Bytes2) {

    return xorCache.computeIfAbsent(input1Bytes2, i -> new ConcurrentHashMap<>()).computeIfAbsent(
            input2Bytes2, i-> UnsignedByte.of(input1Bytes2.xor(input2Bytes2).get(0))
    );
  }

  private static ConcurrentHashMap<Bytes, UnsignedByte> notCache = new ConcurrentHashMap<>();
  public static UnsignedByte not(Bytes input1Bytes2, Bytes input2Bytes2) {
    return notCache.computeIfAbsent(input1Bytes2, i-> UnsignedByte.of(i.not().get(0)));
  }


}
