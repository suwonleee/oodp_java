package org._22_interpreter.ex02;

import java.util.*;

// Abstract Expression
interface Expression {
    List<Map<String, String>> interpret(Context context);
}