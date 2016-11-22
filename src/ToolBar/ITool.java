package ToolBar;

import Commands.ICommand;

/**
 * Created by ahmad on 11/1/2016.
 */
public interface ITool {
    String text = null;

    String GetText();
    void SetText(String text);

    void SetCommand(ICommand command);
    void SetMnemonic(char mnemonic);
}
