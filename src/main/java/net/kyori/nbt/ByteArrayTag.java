/*
 * This file is part of nbt, licensed under the MIT License.
 *
 * Copyright (c) 2017 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Nonnull;

/**
 * A tag representing an array of {@code byte}s.
 */
public final class ByteArrayTag extends Tag {

  /**
   * The array of bytes.
   */
  private byte[] value;

  ByteArrayTag() {
  }

  public ByteArrayTag(@Nonnull final byte[] value) {
    this.value = value;
  }

  /**
   * Gets the array of bytes.
   *
   * @return the array of bytes
   */
  @Nonnull
  public byte[] value() {
    return this.value;
  }

  @Override
  protected void read(final DataInput input, final int depth) throws IOException {
    final int length = input.readInt();
    this.value = new byte[length];
    input.readFully(this.value);
  }

  @Override
  protected void write(final DataOutput output) throws IOException {
    output.writeInt(this.value.length);
    output.write(this.value);
  }

  @Nonnull
  @Override
  public TagType type() {
    return TagType.BYTE_ARRAY;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(this.value);
  }

  @Override
  public boolean equals(final Object that) {
    return this == that || (that instanceof ByteArrayTag && Arrays.equals(this.value, ((ByteArrayTag) that).value));
  }
}
