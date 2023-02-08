## Disable 1.19 Chat Signatures
In 1.19.1 -> 1.19.3, Minecraft signs chat packets with a `IdentifiedKey` to securely send messages & enable the new report message system. This system is pointless to me & as a result, messages sent to the proxy cannot be edited or cancelled using `setResult(PlayerChatEvent.ChatResult.denied());`

To combat this and allow me to cancel messages etc, wrote this little plugin to set the `IdentifiedKey` to `null` when a player connects to the proxy, which intern disables the signature checks when messages are sent, allowing us to delete or modify them.

