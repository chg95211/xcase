﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace XCase.REST.ProxyGenerator.RAML
{
    public class LookAheadBuffer : ILookAheadBuffer
    {
        private readonly TextReader input;
        private readonly char[] buffer;
        private int firstIndex;
        private int count;
        private bool endOfInput;

        /// <summary>
        /// Initializes a new instance of the <see cref="LookAheadBuffer"/> class.
        /// </summary>
        /// <param name="input">The input.</param>
        /// <param name="capacity">The capacity.</param>
        public LookAheadBuffer(TextReader input, int capacity)
        {
            if (input == null)
            {
                throw new ArgumentNullException("input");
            }
            if (capacity < 1)
            {
                throw new ArgumentOutOfRangeException("capacity", "The capacity must be positive.");
            }

            this.input = input;
            buffer = new char[capacity];
        }

        /// <summary>
        /// Gets a value indicating whether the end of the input reader has been reached.
        /// </summary>
        public bool EndOfInput
        {
            get
            {
                return endOfInput && count == 0;
            }
        }

        /// <summary>
        /// Gets the index of the character for the specified offset.
        /// </summary>
        private int GetIndexForOffset(int offset)
        {
            int index = firstIndex + offset;
            if (index >= buffer.Length)
            {
                index -= buffer.Length;
            }
            return index;
        }

        /// <summary>
        /// Gets the character at thhe specified offset.
        /// </summary>
        public char Peek(int offset)
        {
            if (offset < 0 || offset >= buffer.Length)
            {
                throw new ArgumentOutOfRangeException("offset", "The offset must be betwwen zero and the capacity of the buffer.");
            }

            Cache(offset);

            if (offset < count)
            {
                return buffer[GetIndexForOffset(offset)];
            }
            else
            {
                return '\0';
            }
        }

        /// <summary>
        /// Reads characters until at least <paramref name="length"/> characters are in the buffer.
        /// </summary>
        /// <param name="length">
        /// Number of characters to cache.
        /// </param>
        public void Cache(int length)
        {
            while (length >= count)
            {
                var nextChar = input.Read();
                if (nextChar >= 0)
                {
                    var lastIndex = GetIndexForOffset(count);
                    buffer[lastIndex] = (char)nextChar;
                    ++count;
                }
                else
                {
                    endOfInput = true;
                    return;
                }
            }
        }

        /// <summary>
        /// Skips the next <paramref name="length"/> characters. Those characters must have been
        /// obtained first by calling the <see cref="Peek"/> or <see cref="Cache"/> methods.
        /// </summary>
        public void Skip(int length)
        {
            if (length < 1 || length > count)
            {
                throw new ArgumentOutOfRangeException("length", "The length must be between 1 and the number of characters in the buffer. Use the Peek() and / or Cache() methods to fill the buffer.");
            }
            firstIndex = GetIndexForOffset(length);
            count -= length;
        }
    }
}
