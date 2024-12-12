package me.eths.opensg.packet;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;

public class PacketHandler implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        // The user represents the player.
        User user = event.getUser();
        // Identify what kind of packet it is.
        if (event.getPacketType() != PacketType.Play.Client.CHAT_MESSAGE)
            return;
        // Use the correct wrapper to process this packet.
        WrapperPlayClientChatMessage chatMessage = new WrapperPlayClientChatMessage(event);
        // Access the data within the wrapper using its "getters"
        String message = chatMessage.getMessage();
        // Check if the message is "ping"
        if (message.equalsIgnoreCase("ping")) {
            //Respond with a "pong" message to the client.
            user.sendMessage("pong");
        }
    }

}
