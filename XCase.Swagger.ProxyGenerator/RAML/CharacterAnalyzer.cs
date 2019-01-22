﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace XCase.REST.ProxyGenerator.RAML
{
    internal class CharacterAnalyzer<TBuffer> where TBuffer : ILookAheadBuffer
    {
        private readonly TBuffer buffer;

        public CharacterAnalyzer(TBuffer buffer)
        {
            this.buffer = buffer;
        }

        public TBuffer Buffer
        {
            get
            {
                return buffer;
            }
        }

        public bool EndOfInput
        {
            get
            {
                return buffer.EndOfInput;
            }
        }

        public char Peek(int offset)
        {
            return buffer.Peek(offset);
        }

        public void Skip(int length)
        {
            buffer.Skip(length);
        }

        public bool IsAlphaNumericDashOrUnderscore(int offset = 0)
        {
            var character = buffer.Peek(offset);
            return
                (character >= '0' && character <= '9') ||
                (character >= 'A' && character <= 'Z') ||
                (character >= 'a' && character <= 'z') ||
                character == '_' ||
                character == '-';
        }

        public bool IsAscii(int offset = 0)
        {
            return buffer.Peek(offset) <= '\x7F';
        }

        public bool IsPrintable(int offset = 0)
        {
            var character = buffer.Peek(offset);
            return
                character == '\x9' ||
                character == '\xA' ||
                character == '\xD' ||
                (character >= '\x20' && character <= '\x7E') ||
                character == '\x85' ||
                (character >= '\xA0' && character <= '\xD7FF') ||
                (character >= '\xE000' && character <= '\xFFFD');
        }

        public bool IsDigit(int offset = 0)
        {
            var character = buffer.Peek(offset);
            return character >= '0' && character <= '9';
        }

        public int AsDigit(int offset = 0)
        {
            return buffer.Peek(offset) - '0';
        }

        public bool IsHex(int offset)
        {
            var character = buffer.Peek(offset);
            return
                (character >= '0' && character <= '9') ||
                (character >= 'A' && character <= 'F') ||
                (character >= 'a' && character <= 'f');
        }

        public int AsHex(int offset)
        {
            var character = buffer.Peek(offset);

            if (character <= '9')
            {
                return character - '0';
            }
            if (character <= 'F')
            {
                return character - 'A' + 10;
            }
            return character - 'a' + 10;
        }

        public bool IsSpace(int offset = 0)
        {
            return Check(' ', offset);
        }

        public bool IsZero(int offset = 0)
        {
            return Check('\0', offset);
        }

        public bool IsTab(int offset = 0)
        {
            return Check('\t', offset);
        }

        public bool IsWhite(int offset = 0)
        {
            return IsSpace(offset) || IsTab(offset);
        }

        public bool IsBreak(int offset = 0)
        {
            return Check("\r\n\x85\x2028\x2029", offset);
        }

        public bool IsCrLf(int offset = 0)
        {
            return Check('\r', offset) && Check('\n', offset + 1);
        }

        public bool IsBreakOrZero(int offset = 0)
        {
            return IsBreak(offset) || IsZero(offset);
        }

        public bool IsWhiteBreakOrZero(int offset = 0)
        {
            return IsWhite(offset) || IsBreakOrZero(offset);
        }

        public bool Check(char expected, int offset = 0)
        {
            return buffer.Peek(offset) == expected;
        }

        public bool Check(string expectedCharacters, int offset = 0)
        {
            // Todo: using it this way doesn't break anything, it's not realy wrong...
            //Debug.Assert(expectedCharacters.Length > 1, "Use Check(char, int) instead.");

            var character = buffer.Peek(offset);
            return expectedCharacters.IndexOf(character) != -1;
        }
    }
}