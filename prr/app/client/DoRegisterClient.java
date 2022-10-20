package prr.app.client;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    //FIXME add command fields
    addStringField("key", Message.key());
    addStringField("name", Message.name());
    addIntegerField("NIF", Message.taxId());

  }
  
  @Override
  protected final void execute() throws CommandException {
    String key = stringField("key");
    String name = stringField("name");
    Integer taxNumber = integerField("NIF");
    _receiver.registerClient(key, name, taxNumber);
  }
}
