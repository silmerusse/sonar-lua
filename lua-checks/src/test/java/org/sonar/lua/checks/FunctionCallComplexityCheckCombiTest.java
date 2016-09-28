/*
 * SonarQube Lua Plugin
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.lua.checks;

import org.junit.Test;
import org.sonar.lua.LuaAstScanner;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;
import org.sonar.squidbridge.api.SourceFunction;

import java.io.File;

public class FunctionCallComplexityCheckCombiTest {

  @Test
  public void test() {
    FunctionCallComplexityCheck check = new FunctionCallComplexityCheck();
    check.setMaximumFunctionCallComplexityThreshold(1);

    SourceFile file = LuaAstScanner.scanSingleFile(new File("src/test/resources/checks/functionCallComplexityCombi.lua"), check);

    CheckMessagesVerifier.verify(file.getCheckMessages())
        .next().atLine(1).withMessage("FunctionCall has a complexity of 12 which is greater than 1 authorized.")
        .next().atLine(3).withMessage("FunctionCall has a complexity of 2 which is greater than 1 authorized.")
        .next().atLine(31).withMessage("FunctionCall has a complexity of 11 which is greater than 1 authorized.")
        .next().atLine(34).withMessage("FunctionCall has a complexity of 14 which is greater than 1 authorized.") 
        .next().atLine(35).withMessage("FunctionCall has a complexity of 3 which is greater than 1 authorized.") 
        .noMore();
  }

}



