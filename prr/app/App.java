package prr.app;

import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.NetworkManager;
import prr.core.exception.ImportFileException;
import pt.tecnico.uilib.Dialog;

/**
 * Application entry-point.
 */
public class App {

  public static void main(String[] args) throws DuplicateClientKeyException, InvalidTerminalKeyException, UnknownClientKeyException {
    try (var ui = Dialog.UI) {
      var receiver = new NetworkManager();
      
      String datafile = System.getProperty("import");
      if (datafile != null) {
        try {
          receiver.importFile(datafile);
        } catch (ImportFileException e) {
          // no behavior described: just present the problem
          e.printStackTrace();
        }
      }
      
      (new prr.app.main.Menu(receiver)).open();
    }
  }
}
