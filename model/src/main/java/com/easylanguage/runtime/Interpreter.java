package com.easylanguage.runtime;

import com.easylanguage.generated.EasyLanguageBaseListener;
import com.easylanguage.generated.EasyLanguageParser;

public class Interpreter extends EasyLanguageBaseListener {
    private SymbolTable symbols = new SymbolTable();

    @Override
    public void exitDecl(EasyLanguageParser.DeclContext ctx) {
        String varName = ctx.ID().getText();
        symbols.declare(varName);
    }

    @Override
    public void exitAssign(EasyLanguageParser.AssignContext ctx) {
        String varName = ctx.ID().getText();
        int value = Integer.parseInt(ctx.NUMBER().getText());
        symbols.assign(varName, value);
    }

    @Override
    public void exitWrite(EasyLanguageParser.WriteContext ctx) {
        String varName = ctx.ID().getText();
        System.out.println(symbols.get(varName));
    }
}
