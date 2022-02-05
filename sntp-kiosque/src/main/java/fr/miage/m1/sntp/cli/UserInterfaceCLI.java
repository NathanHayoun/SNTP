package fr.miage.m1.sntp.cli;

import org.beryx.textio.TextIO;

import java.util.function.BiConsumer;

public interface UserInterfaceCLI extends BiConsumer<TextIO, RunnerData>, UserInterface {
}
