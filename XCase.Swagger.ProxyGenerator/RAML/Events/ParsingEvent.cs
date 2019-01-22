﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace XCase.REST.ProxyGenerator.RAML.Events
{
    public abstract class ParsingEvent
    {
        /// <summary>
        /// Gets a value indicating the variation of depth caused by this event.
        /// The value can be either -1, 0 or 1. For start events, it will be 1,
        /// for end events, it will be -1, and for the remaining events, it will be 0.
        /// </summary>
        public virtual int NestingIncrease
        {
            get { return 0; }
        }

        /// <summary>
        /// Gets the event type, which allows for simpler type comparisons.
        /// </summary>
        internal abstract EventType Type
        {
            get;
        }

        private readonly Mark start;

        /// <summary>
        /// Gets the position in the input stream where the event starts.
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
        /// Gets the position in the input stream where the event ends.
        /// </summary>
        public Mark End
        {
            get
            {
                return end;
            }
        }

        /// <summary>
        /// Accepts the specified visitor.
        /// </summary>
        /// <param name="visitor">Visitor to accept, may not be null</param>
        public abstract void Accept(IParsingEventVisitor visitor);

        /// <summary>
        /// Initializes a new instance of the <see cref="ParsingEvent"/> class.
        /// </summary>
        /// <param name="start">The start position of the event.</param>
        /// <param name="end">The end position of the event.</param>
        internal ParsingEvent(Mark start, Mark end)
        {
            this.start = start;
            this.end = end;
        }
    }
}
