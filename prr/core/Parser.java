package prr.core;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.UnrecognizedEntryException;

/* 
 * A concretização desta classe depende da funcionalidade suportada pelas entidades do core:
 *  - adicionar um cliente e terminal a uma rede de terminais;
 *  - indicar o estado de um terminal
 *  - adicionar um amigo a um dado terminal
 * A forma como estas funcionalidades estão concretizaas terão impacto depois na concretização dos
 * métodos parseClient, parseTerminal e parseFriends
 */

public class Parser {
  private Network _network;

  Parser(Network network) {
    _network = network;
  }

  void parseFile(String filename)
      throws IOException, UnrecognizedEntryException, InvalidTerminalKeyException, UnknownClientKeyException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null) {
        parseLine(line);
      }
    }
  }

  private void parseLine(String line)
      throws UnrecognizedEntryException, InvalidTerminalKeyException, UnknownClientKeyException {
    String[] components = line.split("\\|");

    switch (components[0]) {
      case "CLIENT":
        parseClient(components, line);
        break;
      case "BASIC":
        break;
      case "FANCY":
        parseTerminal(components, line);
        break;
      case "FRIENDS":
        parseFriends(components, line);
        break;
      default:
        throw new UnrecognizedEntryException("Line with wong type: " + components[0]);
    }
  }

  private void checkComponentsLength(String[] components, int expectedSize, String line)
      throws UnrecognizedEntryException {
    if (components.length != expectedSize) // isto originalmente era component.length acho que era um erro
      throw new UnrecognizedEntryException("Invalid number of fields in line: " + line);
  }

  // parse a client with format CLIENT|id|nome|taxId
  private void parseClient(String[] components, String line) throws UnrecognizedEntryException {
    checkComponentsLength(components, 4, line);

    try {
      int taxNumber = Integer.parseInt(components[3]);
      _network.registerClient(components[1], components[2], taxNumber);
    } catch (NumberFormatException nfe) {
      throw new UnrecognizedEntryException("Invalid number in line " + line, nfe);
    } catch (DuplicateClientKeyException e) { // secalhar e isto porque o registar client tem esta exception
      throw new UnrecognizedEntryException("Invalid specification in line: " + line, e);
    }
  }

  // parse a line with format terminal-type|idTerminal|idClient|state
  private void parseTerminal(String[] components, String line)
      throws UnrecognizedEntryException, UnknownClientKeyException {

    checkComponentsLength(components, 4, line);

    try {
      Terminal terminal = _network.registerTerminal(components[0], components[1], components[2]);
      switch (components[3]) {
        case "SILENCE":
          terminal.setOnSilent();
          break;
        case "OFF":
          terminal.turnOff(); // isto originalmente era terminal->turnOff(); acho que era um erro
          break;
        case "ON":
          break;
        default:
          throw new UnrecognizedEntryException("Invalid specification in line: " + line);
      }
    } catch (InvalidTerminalKeyException | DuplicateTerminalKeyException itke) {
      throw new UnrecognizedEntryException("Invalid specification: " + line, itke);
    }
  }

  // Parse a line with format FRIENDS|idTerminal|idTerminal1,...,idTerminalN
  private void parseFriends(String[] components, String line) throws UnrecognizedEntryException {
    checkComponentsLength(components, 3, line);

    try {
      String terminal = components[1];
      String[] friends = components[2].split(",");

      for (String friend : friends)
        _network.addFriend(terminal, friend);
    } catch (UnknownTerminalKeyException utke) {
      throw new UnrecognizedEntryException("Some message error in line:  " + line, utke);
    }
  }
}
