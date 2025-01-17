package kdf11_proj1;
/**
 * An exception that is thrown when a set operation cannot be completed because
 * the set does not have the available capacity. Should never be thrown in
 * implementations that resize.
 */
public class SetFullException extends Exception {
    public SetFullException() { super(); }
    public SetFullException(String e) { super(e); }
}

