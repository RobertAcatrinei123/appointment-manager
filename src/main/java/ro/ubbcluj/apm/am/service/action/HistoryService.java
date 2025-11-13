package ro.ubbcluj.apm.am.service.action;

import java.util.Stack;

public class HistoryService implements UndoableService {
    private final Stack<Action> undoStack;
    private final Stack<Action> redoStack;

    public HistoryService() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void addAction(Action action) {
        undoStack.push(action);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            action.executeUndo();
            redoStack.push(action);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            action.executeRedo();
            undoStack.push(action);
        }
    }
}
