package ru.itvitality.sbrf.cu.l08.serialization.protobuf;


import com.google.protobuf.util.JsonFormat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        var messageOut = HelloWorld.HelloMessage.newBuilder()
                .setMessage("Hello, World, from Protobuf")
                .addArrayIntData(10)
                .addArrayIntData(20)
                .addArrayIntData(30)
                .build();
        var fileName = "protoTest.bin";

        try (FileOutputStream output = new FileOutputStream(fileName)) {
            messageOut.writeTo(output);
        }

        try (FileInputStream input = new FileInputStream(fileName)) {
            var messageIn = HelloWorld.HelloMessage.parseFrom(input);
            System.out.println("messageIn:"  + messageIn.getMessage() + ",  array:" + messageIn.getArrayIntDataList());
        }

        var messageJson = JsonFormat.printer().print(messageOut);
        System.out.println("messageJson:" + messageJson);

        var builder = HelloWorld.HelloMessage.newBuilder();
        JsonFormat.parser().merge(messageJson, builder);
        var messageFromJson = builder.build();
        System.out.println("messageFromJson:" + messageFromJson.getMessage() + ", array:" + messageFromJson.getArrayIntDataList());
    }
}
