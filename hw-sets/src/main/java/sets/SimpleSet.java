/*
 * Copyright (C) 2022 Kevin Zatloukal and James Wilcox.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package sets;

import java.util.List;

/**
 * Represents an immutable set of points on the real line that is easy to
 * describe, either because it is a finite set, e.g., {p1, p2, ..., pN}, or
 * because it excludes only a finite set, e.g., R \ {p1, p2, ..., pN}. As with
 * FiniteSet, each point is represented by a Java float with a non-infinite,
 * non-NaN value.
 */
public class SimpleSet {

  private FiniteSet finite;
  private FiniteSet infinite;

  // TODO: fill in and document the representation
  //       Make sure to include the representation invariant (RI)
  // REP Invariant: A simple set is a set that must contain a finite set of
  // values and will be able to compare 2 different sets. A finite set and an infinite
  // set The finite set will != null the infinite set will include a finite set not in the infinite set.
  // AF: The abstraction function uses 2 FiniteSets, finite and infinite. S0 = infinite set
  // S1 = finite set, S0 == null && S1 == null || S0 != null && S1 != null
  //
  //
  private void checkRep() {
    if (finite == null && infinite == null || finite != null && infinite != null) {
      throw new IllegalStateException();
    }
  }

  /**
   * Creates a simple set containing only the given points.
   *
   * @param vals Array containing the points to make into a SimpleSet
   * @spec.requires points != null and has no NaNs, no infinities, and no dups
   * @spec.effects this = {vals[0], vals[1], ..., vals[vals.length-1]}
   */
  public SimpleSet(float[] vals) {
    finite = FiniteSet.of(vals);
    infinite = null;

  }

  private SimpleSet(FiniteSet finite, FiniteSet infinite) {
    this.finite = finite;
    this.infinite = infinite;
    checkRep();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof SimpleSet))
      return false;

    SimpleSet other = (SimpleSet) o;
    checkRep();
    other.checkRep();
    if (checkFinite()) {
      return this.finite.equals(other.finite);
    }
    return this.infinite.equals(other.infinite);

    //Another way for the same thing
    //return(this.finite != null) ?
    // this.finite.equals(other.finite) : this.infinite.equals(other.infinite);
  }

  private boolean checkFinite() {
    return (this.finite != null);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns the number of points in this set.
   *
   * @return N      if this = {p1, p2, ..., pN} and
   * infty  if this = R \ {p1, p2, ..., pN}
   */
  public float size() {
    if(checkFinite()){
      return finite.size();
    }
    return Float.POSITIVE_INFINITY;
  }

  /**
   * Returns a string describing the points included in this set.
   *
   * @return the string "R" if this contains every point,
   * a string of the form "R \ {p1, p2, .., pN}" if this contains all
   * but {@literal N > 0} points, or
   * a string of the form "{p1, p2, .., pN}" if this contains
   * {@literal N >= 0} points,
   * where p1, p2, ... pN are replaced by the individual numbers. These
   * floats will be turned into strings in the standard manner (the same as
   * done by, e.g., String.valueOf(float)).
   */
  public String toString() {
    // TODO: implement this with a loop. document its invariant
    //       a StringBuilder may be useful for creating the string
    //Loop Invariant. StringBuilder = "" If set is infinite StringBuilder = "R \ {each value not in infinite set +, }" else
    // StringBuilder = "{each value in the set +, }"
    checkRep();
    StringBuilder string = new StringBuilder();
    string.append(checkFinite() ? "{" : "R \\ {");
    List<Float> valList = checkFinite() ? finite.getPoints() : infinite.getPoints();
    for (int i = 0; i < valList.size(); i++) {
      string.append(valList.get(i));
      if (i != valList.size() - 1) {
        string.append(", ");
      }
    }
    string.append("}");
    return string.toString();
  }

  /**
   * Returns a set representing the points R \ this.
   *
   * @return R \ this
   */
  public SimpleSet complement() {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct (hint: cases)
    // The complement is correct because you just switch the place of the infinite set and the finite set to
    // it to be able to be the complement
    return new SimpleSet(infinite, finite);
  }

  /**
   * Returns the union of this and other.
   *
   * @param other Set to union with this one.
   * @return this union other
   * @spec.requires other != null
   */
  public SimpleSet union(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct (hint: cases)
    // This method is correct because it covers all 4 cases of both sets being finite,
    // infinite. Then 1 set being infinite and 1 set being finite and vise-versa
    // I then made sure I knew the union of the set would either be infinite or finite.
    // After that I correctly changed the set to be the union of those 2 sets

    if (this.checkFinite() && other.checkFinite()) {

      return new SimpleSet(finite.union(other.finite), null);

    } else if (!checkFinite() && other.checkFinite()) {

      return new SimpleSet(null, this.infinite.difference(other.finite));

    } else if (checkFinite() && !other.checkFinite()) {

      return new SimpleSet(null, other.infinite.difference(this.finite));

    } else {

      return new SimpleSet(null, infinite.intersection(other.infinite));
    }

  }

  /**
   * Returns the intersection of this and other.
   *
   * @param other Set to intersect with this one.
   * @return this intersected with other
   * @spec.requires other != null
   */
  public SimpleSet intersection(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // This method is correct because it covers all 4 cases of both sets being finite and
    // infinite. Then 1 set being infinite and 1 set being finite and vise-versa
    // I then made sure I knew the intersection of the set would either be infinite or finite.
    // After that I correctly changed the set to be the intersection of those 2 sets

    if (this.checkFinite() && other.checkFinite()) {

      return new SimpleSet(finite.intersection(other.finite), null);

    } else if (!checkFinite() && other.checkFinite()) {

      return new SimpleSet(other.finite.difference(this.infinite), null);

    } else if (checkFinite() && !other.checkFinite()) {

      return new SimpleSet(this.finite.difference(other.infinite), null);

    } else {

      return new SimpleSet(null, infinite.union(other.infinite));
    }
  }

  /**
   * Returns the difference of this and other.
   *
   * @param other Set to difference from this one.
   * @return this minus other
   * @spec.requires other != null
   */
  public SimpleSet difference(SimpleSet other) {
    // TODO: implement this method
    //       include sufficient comments to see why it is correct
    // This method is correct because the difference of the 2 sets is the intersection
    // of this and the complement of other

    return this.intersection(other.complement());
  }
}