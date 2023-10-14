
package controller;

import Repository.FileRepository;
import Repository.IFileRepository;
import view.Menu;


public class File_Processing extends Menu<String> {
    private IFileRepository fileRepository;
    private static final String[] MENU_CHOICES = {"Find person info.", "Copy Text to new file.", "Exit"};

    public File_Processing() {
        super("========== File Processing =========", MENU_CHOICES);
        fileRepository = new FileRepository();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> fileRepository.getPerson();
            case 2 -> fileRepository.copyWordOneTimes();
            case 3 -> System.exit(0);
            default -> System.err.println("Invalid choice. Please try again.");
        }
    }
}