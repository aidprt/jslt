
package com.schibsted.spt.data.json;

import java.util.Iterator;
import com.schibsted.spt.data.jslt.JsltException;

public class SmallJObject extends AbstractJsonValue {
  private String[] keys;
  private JsonValue[] values;
  private int nextIx;

  public SmallJObject(int size) {
    this.keys = new String[size];
    this.values = new JsonValue[size];
  }

  public boolean isObject() {
    return true;
  }

  public int size() {
    return nextIx;
  }

  public boolean has(String key) {
    for (int ix = 0; ix < keys.length; ix++)
      if (key.equals(keys[ix]))
        return true;
    return false;
  }

  public JsonValue get(String key) {
    for (int ix = 0; ix < keys.length; ix++)
      if (key.equals(keys[ix]))
        return values[ix];
    return null;
  }

  public Iterator<String> getKeys() {
    return new KeyIterator();
  }

  public PairIterator pairIterator() {
    return new SmallPairIterator();
  }

  public int hashCode() {
    throw new JsltException("OUCH");
  }

  public boolean equals(Object other) {
    if (other instanceof JsonValue) {
      JsonValue v = (JsonValue) other;
      if (v.isObject() && v.size() == size()) {
        throw new JsltException("OUCH");
      }
      return false;
    } else
      return false;
  }

  public String toString() {
    throw new JsltException("OUCH");
  }

  // modifications

  public void set(String key, JsonValue value) {
    keys[nextIx] = key;
    values[nextIx++] = value;
  }

  // ===== KEY ITERATOR

  class KeyIterator implements Iterator<String> {
    private int ix;

    public boolean hasNext() {
      return ix < nextIx;
    }

    public String next() {
      return keys[ix++];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  // ===== PAIR ITERATOR

  class SmallPairIterator implements PairIterator {
    private int ix;

    public boolean hasNext() {
      return ix < keys.length;
    }

    public String key() {
      return keys[ix];
    }

    public JsonValue value() {
      return values[ix];
    }

    public void next() {
      ix++;
    }
  }
}
