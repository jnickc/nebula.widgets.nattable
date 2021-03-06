/*******************************************************************************
 * Copyright (c) 2012, 2017 Original authors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Original authors and others - initial API and implementation
 ******************************************************************************/
package org.eclipse.nebula.widgets.nattable.resize.command;

import org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;

public class ColumnResizeCommandHandler extends AbstractLayerCommandHandler<ColumnResizeCommand> {

    private final DataLayer dataLayer;

    public ColumnResizeCommandHandler(DataLayer dataLayer) {
        this.dataLayer = dataLayer;
    }

    @Override
    public Class<ColumnResizeCommand> getCommandClass() {
        return ColumnResizeCommand.class;
    }

    @Override
    protected boolean doCommand(ColumnResizeCommand command) {
        int newColumnWidth = command.downScaleValue()
                ? this.dataLayer.downScaleColumnWidth(command.getNewColumnWidth())
                : command.getNewColumnWidth();
        this.dataLayer.setColumnWidthByPosition(command.getColumnPosition(), newColumnWidth);
        return true;
    }

}
