package com.thaiopensource.relaxng.edit;

public class OptionalPattern extends UnaryPattern {
  public OptionalPattern(Pattern child) {
    super(child);
  }

  Object accept(PatternVisitor visitor) {
    return visitor.visitOptional(this);
  }
}