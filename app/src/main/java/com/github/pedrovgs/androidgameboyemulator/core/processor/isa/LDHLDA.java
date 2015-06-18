package com.github.pedrovgs.androidgameboyemulator.core.processor.isa;

import com.github.pedrovgs.androidgameboyemulator.core.mmu.MMU;
import com.github.pedrovgs.androidgameboyemulator.core.processor.GBZ80;

public class LDHLDA extends Instruction {

  LDHLDA(GBZ80 z80, MMU mmu) {
    super(z80, mmu);
  }

  @Override public void execute() {
    int address = (z80.getRegisterH() << 8) + z80.getRegisterL();
    int value = z80.getRegisterA();
    mmu.writeByte(address, value);
    int registerValue = (z80.getRegisterL() - 1) & 255;
    z80.setRegisterL(registerValue);
    if (z80.getRegisterL() == 255) {
      int registerHvalue = (z80.getRegisterH() - 1) & 255;
      z80.setRegisterH(registerHvalue);
    }
    z80.setLastInstructionExecutionTime(2);
  }
}