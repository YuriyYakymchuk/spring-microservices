package com.ogasimov.labs.springcloud.microservices.common;

public class FinishDinnerCommand extends AbstractDinnerCommand {

    public FinishDinnerCommand() {}

    public FinishDinnerCommand(Integer tableId) {
        super(tableId);
    }
}
