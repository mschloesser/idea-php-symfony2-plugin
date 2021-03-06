package fr.adrienbrault.idea.symfony2plugin.util.dict;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.jetbrains.php.completion.insert.PhpInsertHandlerUtil;
import com.jetbrains.php.lang.psi.elements.PhpClass;
import fr.adrienbrault.idea.symfony2plugin.util.PsiElementUtils;
import org.jetbrains.annotations.NotNull;


public class ResourceFileInsertHandler implements InsertHandler<LookupElement> {

    private static final ResourceFileInsertHandler instance = new ResourceFileInsertHandler();

    public void handleInsert(@NotNull InsertionContext context, @NotNull LookupElement lookupElement) {

        // resource: "@AsseticBundle/Resources/config/filters/cssimport.xml"
        // if no @ is before cursor add one
        if(!isStringBeforeCaret(context.getEditor(), context, "@")) {
            context.getDocument().insertString(context.getStartOffset(), "@");
        }

    }

    public static boolean isStringBeforeCaret(@NotNull Editor editor, InsertionContext context, @NotNull String string) {

        String fileText = editor.getDocument().getText();
        if (fileText.length() < string.length()) {
            return false;
        }

        return fileText.substring(context.getStartOffset() - string.length(), context.getStartOffset()).equals(string);
    }


    public static ResourceFileInsertHandler getInstance(){
        return instance;
    }

}