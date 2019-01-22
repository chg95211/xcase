﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace XCase.REST.ProxyGenerator.RAML.Tokens
{
    public abstract class Token
    {
        private readonly Mark start;

        /// <summary>
        /// Gets the start of the token in the input stream.
        /// </summary>
        public Mark Start
        {
            get
            {
                return start;
            }
        }

        private readonly Mark end;

        /// <summary>
        /// Gets the end of the token in the input stream.
        /// </summary>
        public Mark End
        {
            get
            {
                return end;
            }
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="Token"/> class.
        /// </summary>
        /// <param name="start">The start position of the token.</param>
        /// <param name="end">The end position of the token.</param>
        protected Token(Mark start, Mark end)
        {
            this.start = start;
            this.end = end;
        }
    }
}
