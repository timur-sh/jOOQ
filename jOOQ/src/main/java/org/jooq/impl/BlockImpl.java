/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

// ...
// ...
import static org.jooq.conf.ParamType.INLINED;
import static org.jooq.impl.Keywords.K_BEGIN;
import static org.jooq.impl.Keywords.K_DO;
import static org.jooq.impl.Keywords.K_END;
import static org.jooq.impl.Keywords.K_EXECUTE_IMMEDIATE;
import static org.jooq.impl.Keywords.K_NULL;
import static org.jooq.impl.Tools.decrement;
import static org.jooq.impl.Tools.increment;
import static org.jooq.impl.Tools.DataKey.DATA_BLOCK_NESTING;
import static org.jooq.impl.Tools.DataKey.DATA_FORCE_STATIC_STATEMENT;

import java.util.Collection;
import java.util.EnumSet;

import org.jooq.Block;
import org.jooq.Clause;
import org.jooq.Configuration;
import org.jooq.Context;
import org.jooq.DDLQuery;
import org.jooq.SQLDialect;
import org.jooq.Statement;

/**
 * @author Lukas Eder
 */
final class BlockImpl extends AbstractQuery implements Block {

    /**
     * Generated UID
     */
    private static final long                     serialVersionUID                  = 6881305779639901498L;





    private final Collection<? extends Statement> statements;

    BlockImpl(Configuration configuration, Collection<? extends Statement> statements) {
        super(configuration);

        this.statements = statements;
    }

    @Override
    public final void accept(Context<?> ctx) {
        switch (ctx.family()) {
            case POSTGRES: {
                if (increment(ctx.data(), DATA_BLOCK_NESTING)) {
                    ctx.paramType(INLINED)
                       .visit(K_DO).sql(" $$")
                       .formatSeparator();

                    ctx.data(DATA_FORCE_STATIC_STATEMENT, true);
                }

                accept0(ctx);

                if (decrement(ctx.data(), DATA_BLOCK_NESTING))
                    ctx.formatSeparator()
                       .sql("$$");

                break;
            }















            default: {
                accept0(ctx);
                break;
            }
        }
    }

    private final void accept0(Context<?> ctx) {
        ctx.visit(K_BEGIN)
           .formatIndentStart();

        if (statements.isEmpty()) {
            switch (ctx.family()) {






                default:
                    ctx.formatSeparator().visit(K_NULL).sql(';');
                    break;
            }
        }
        else {
            for (Statement query : statements) {
                ctx.formatSeparator();







                ctx.visit(query);






                if (!(query instanceof Block))
                    ctx.sql(';');
            }
        }

        ctx.formatIndentEnd()
           .formatSeparator()
           .visit(K_END);

        switch (ctx.family()) {





            default:
                ctx.sql(';');
        }
    }

    @Override
    public final Clause[] clauses(Context<?> ctx) {
        return null;
    }
}
