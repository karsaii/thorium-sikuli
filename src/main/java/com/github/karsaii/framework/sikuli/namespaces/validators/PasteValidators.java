package com.github.karsaii.framework.sikuli.namespaces.validators;

import com.github.karsaii.core.namespaces.validators.CoreFormatter;

public interface PasteValidators {
    static String isValidInput(String input) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("isValidInput", CoreFormatter.isBlankMessageWithName(input, "Input"));
    }

    static String getPasteValidParametersMessage(String target, double percentage, int index, String input) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getPasteValidParametersMessage", (
            GetElementsValidators.getElementParametersValidMessage(target, percentage, index) +
            isValidInput(input)
        ));
    }

    static String getPasteValidParametersMessage(String target, double percentage, String input) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getPasteValidParametersMessage", (
                GetElementsValidators.getElementParametersValidMessage(target, percentage) +
                isValidInput(input)
        ));
    }

    static String getPasteValidParametersMessage(String target, int index, String input) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getPasteValidParametersMessage", (
            GetElementsValidators.getElementParametersValidMessage(target, index) +
            isValidInput(input)
        ));
    }

    static String getPasteValidParametersMessage(String target, String input) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getPasteValidParametersMessage", (
            GetElementsValidators.getValidLocatorMessage(target) +
            isValidInput(input)
        ));
    }

    static String getPasteValidParametersMessageText(String text, String input) {
        return CoreFormatter.getNamedErrorMessageOrEmpty("getPasteValidParametersMessage", (
            GetElementsValidators.getValidTextLocatorMessage(text) +
            isValidInput(input)
        ));
    }
}
