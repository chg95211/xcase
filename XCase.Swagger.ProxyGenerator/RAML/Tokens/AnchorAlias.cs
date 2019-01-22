﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace XCase.REST.ProxyGenerator.RAML.Tokens
{
    public class AnchorAlias : Token
    {
        private readonly string value;

        /// <summary>
        /// Gets the value of the alias.
        /// </summary>
        public string Value
        {
            get
            {
                return value;
            }
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="AnchorAlias"/> class.
        /// </summary>
        /// <param name="value">The value of the anchor.</param>
        public AnchorAlias(string value)
            : this(value, Mark.Empty, Mark.Empty)
        {
        }

        /// <summary>
        /// Initializes a new instance of the <see cref="AnchorAlias"/> class.
        /// </summary>
        /// <param name="value">The value of the anchor.</param>
        /// <param name="start">The start position of the event.</param>
        /// <param name="end">The end position of the event.</param>
        public AnchorAlias(string value, Mark start, Mark end)
            : base(start, end)
        {
            this.value = value;
        }
    }
}
