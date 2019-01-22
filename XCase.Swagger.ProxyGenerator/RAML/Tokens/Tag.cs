﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace XCase.REST.ProxyGenerator.RAML.Tokens
{
    class Tag : Token
    {
        private readonly string handle;
        private readonly string suffix;

        /// <summary>
        /// Gets the handle.
        /// </summary>
        /// <value>The handle.</value>
        public string Handle
        {
            get
            {
                return handle;
            }
        }

        /// <summary>
        /// Gets the suffix.
        /// </summary>
        /// <value>The suffix.</value>
        public string Suffix
        {
            get
            {
                return suffix;
            }
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="Tag"/> class.
        /// </summary>
        /// <param name="handle">The handle.</param>
        /// <param name="suffix">The suffix.</param>
        public Tag(string handle, string suffix)
            : this(handle, suffix, Mark.Empty, Mark.Empty)
        {
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="Tag"/> class.
        /// </summary>
        /// <param name="handle">The handle.</param>
        /// <param name="suffix">The suffix.</param>
        /// <param name="start">The start position of the token.</param>
        /// <param name="end">The end position of the token.</param>
        public Tag(string handle, string suffix, Mark start, Mark end)
            : base(start, end)
        {
            this.handle = handle;
            this.suffix = suffix;
        }
    }
}
