package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Main extends Application {
	static CircularDoubleList<Student> dl = new CircularDoubleList<Student>();
	static AVL<Integer> id = new AVL<Integer>();
	static AVL<Double> grade = new AVL<Double>();
	static String nameBranch = "";

	@Override
	public void start(Stage stage) throws IOException {

//        stage.setScene(Main.Menu(stage));
//        stage.show();

		Pane Read = new Pane();
		HBox ReadHB = new HBox();
		RadioButton Lit = new RadioButton("Literary");
		RadioButton Sci = new RadioButton("Scientific");
		ToggleGroup ReadTG = new ToggleGroup();
		Lit.setToggleGroup(ReadTG);
		Sci.setToggleGroup(ReadTG);
		ReadHB.getChildren().addAll(Lit, Sci);
		ReadHB.setSpacing(10);
		Read.getChildren().addAll(ReadHB);
		ReadHB.relocate(300, 100);// add to pane
		Scene ReadScene = new Scene(Read, 500, 500);
		Label lableR = new Label("Please Select The Branch Type:");
		lableR.setLayoutX(110);
		lableR.setLayoutY(100);
		Button ReadFile = new Button("Read File");
		ReadFile.setLayoutX(210);
		ReadFile.setLayoutY(180);
		Button Next = new Button("Next-->");
		Next.setLayoutX(210);
		Next.setLayoutY(240);

		Lit.setOnAction(e -> {
			nameBranch = "Literary";
		});

		Sci.setOnAction(e -> {
			nameBranch = "Scientific";
		});

//		Read.setBackground(new Background(new BackgroundImage(new Image("tw.png"), null, null, null, null)));
		Read.getChildren().addAll(lableR, ReadFile, Next);

		ReadFile.setOnAction(e -> {
			try {
				RadioButton chk = (RadioButton) ReadTG.getSelectedToggle();
				if (chk.getText().equals(nameBranch)) {
					FileChooser choser = new FileChooser();
					File mo = choser.showOpenDialog(stage);
					if (mo.exists()) {
						try {
							Scanner in = new Scanner(mo);
							while (in.hasNextLine()) {
								String x[] = in.nextLine().split(",");
								if (x[1].trim().equals(nameBranch)) {
									dl.insertEnd(new Student(Integer.parseInt(x[0]), x[1], Double.parseDouble(x[2])));
									id.insert(Integer.parseInt(x[0]));
									TNode node = grade.FindNode(Double.parseDouble(x[2]));

									if (node == null) {
//										System.out.println(1111);
										grade.insert(Double.parseDouble(x[2]));
//										System.out.println(1);
										TNode node2 = grade.FindNode(Double.parseDouble(x[2]));
//										System.out.println(12);
										node2.linkedList.insertLast(
												new Student(Integer.parseInt(x[0]), x[1], Double.parseDouble(x[2])));
//										System.out.println(2);
										dl.head.prev.curr1 = node2;
//										System.out.println(3);
										TNode node1 = id.FindNode1(Integer.parseInt(x[0]));
//										System.out.println(4);
										dl.head.prev.curr = node1;
//										System.out.println(5);

									} else {
//										System.out.println(node.data.toString());
//										System.out.println(1);
										node.linkedList.insertLast(
												new Student(Integer.parseInt(x[0]), x[1], Double.parseDouble(x[2])));
//										System.out.println(2);
										dl.head.prev.curr1 = node;
//										System.out.println(3);
										TNode node1 = id.FindNode1(Integer.parseInt(x[0]));
//										System.out.println(4);
										dl.head.prev.curr = node1;
//										System.out.println(5);
									}
								}
							}
						} catch (FileNotFoundException en) {
							en.printStackTrace();
						}
						ReadFile.setDisable(true);
						dl.travers();
//						System.out.println("\n");
						id.traversalInOrder();
//						System.out.println("\n");
						grade.traversalInOrder();
//						System.out.println(
//								"\n root " + grade.root + " right :" + grade.root.rigth + " left :" + grade.root.left);
					} else
						System.out.println("File not exist");

				} else {
					Alert alert = new Alert(Alert.AlertType.NONE, "YOU should select branchooo", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					return;
				}
			} catch (Exception t) {
				Alert alert = new Alert(Alert.AlertType.NONE, "YOU should select branch", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
				return;
			}
		});

//			Read(stage);
//			ReadWest.setDisable(true);

//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||		

		Pane home = new Pane();
		Scene homeScene = new Scene(home, 500, 500);
		Button insert = new Button("Insert");
		insert.setLayoutX(210);
		insert.setLayoutY(100);
		Button up = new Button("Uptade");
		up.setLayoutX(210);
		up.setLayoutY(140);
		Button delet = new Button("Delete");
		delet.setLayoutX(210);
		delet.setLayoutY(180);
		Button find = new Button("find");
		find.setLayoutX(210);
		find.setLayoutY(220);
		Button DoubleLink = new Button("print Double List");
		DoubleLink.setLayoutX(210);
		DoubleLink.setLayoutY(260);
		Button heigth = new Button("Heigth AVL's");
		heigth.setLayoutX(210);
		heigth.setLayoutY(300);
		home.getChildren().addAll(insert, delet, find, DoubleLink, up, heigth);

//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		GridPane ins = new GridPane();
		Scene insertScene = new Scene(ins, 500, 500);
		TextField seatNumber = new TextField();
		TextField avrage = new TextField();
		TextField branch = new TextField();
		Next.setOnAction(e -> {
			if (Lit.isSelected())
				branch.setText("Literary");
			if (Sci.isSelected())
				branch.setText("Scientific");
			if (!Lit.isSelected() && !Sci.isSelected()) {
				Alert alert = new Alert(Alert.AlertType.NONE, "you should select the branch type", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
				return;
			}
			stage.setScene(homeScene);
			stage.show();
		});
		branch.setDisable(true);
		ins.add(new Label("Seat Number :"), 2, 1);
		ins.add(seatNumber, 3, 1);
		ins.add(new Label("Branch :"), 2, 2);
		ins.add(branch, 3, 2);
		ins.add(new Label("Gread is :"), 2, 3);
		ins.add(avrage, 3, 3);
		ins.setHgap(50);
		ins.setVgap(50);
		Button InsertBt = new Button("Insert -->");
		avrage.setText("");
		ins.add(InsertBt, 3, 5);
		Button back = new Button("<-- Back");
		ins.add(back, 4, 5);

		insert.setOnAction(e -> {
			stage.setScene(insertScene);
		});

		InsertBt.setOnAction(e -> {
			try {

				if (dl.searched(new Student(Integer.parseInt(seatNumber.getText()), branch.getText().trim(),
						Double.parseDouble(avrage.getText()))) == false) {

					if ((Double.parseDouble(avrage.getText()) >= 100.0) || (avrage.getText().equals(null))) {
						Alert alert = new Alert(Alert.AlertType.NONE, "not corect gread value", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						return;
					} else if (seatNumber.getText().equals(null)) {
						Alert alert = new Alert(Alert.AlertType.NONE, "not corect seat number value", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
						return;
					} else {
						dl.insertEnd(new Student(Integer.parseInt(seatNumber.getText()), branch.getText().trim(),
								Double.parseDouble(avrage.getText())));
						id.insert(Integer.parseInt(seatNumber.getText().trim()));
						DNode node = dl.searchedID(Integer.parseInt(seatNumber.getText()));
						Student student = (Student) node.getData();
						TNode gradeNode = grade.FindNode(student.getAverage());
						if (gradeNode != null) {
							gradeNode.linkedList.insertLast(student);
						} else {
							grade.insert(Double.parseDouble(avrage.getText().trim()));
							TNode gradeNode1 = grade.FindNode(Double.parseDouble(avrage.getText().trim()));
							gradeNode1.linkedList.insertLast(student);
						}
						Alert alert = new Alert(Alert.AlertType.NONE, "Has been added", ButtonType.OK);
						if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
						}
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.NONE, "can not add this student", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				}
			} catch (Exception t) {
				Alert alert = new Alert(Alert.AlertType.NONE, "not corect values", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			}
		});

		back.setOnAction(e -> {
			stage.setScene(homeScene);
			stage.show();
		});

//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
		GridPane up1 = new GridPane();
		Scene upScene = new Scene(up1, 600, 500);
		TextField upNumber = new TextField();
		TextField upavrage = new TextField();
		up1.add(new Label("inter seat Number to uptade information:"), 1, 1);
		up1.add(upNumber, 3, 1);
		up1.add(new Label("inter new Avrage :"), 1, 3);
		up1.add(upavrage, 3, 3);
		up1.setHgap(50);
		up1.setVgap(50);
		Button upBT = new Button("UPTADE");
		upavrage.setText("");
		up1.add(upBT, 1, 5);
		Button fofo = new Button("Find");
		up1.add(fofo, 2, 2);
		Button upback = new Button("<-- Back");
		up1.add(upback, 3, 5);

		up.setOnAction(e -> {
			stage.setScene(upScene);
		});

		fofo.setOnAction(e -> {
			try {
				if (dl.findID(Integer.parseInt(upNumber.getText()))) {
					Alert alert = new Alert(Alert.AlertType.NONE, "has been found", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.NONE, "does not found", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				}
			} catch (Exception t) {
				Alert alert = new Alert(Alert.AlertType.NONE, "not corect seatNumber value", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			}
//			DNode node = dl.searchedID(Integer.parseInt(upNumber.getText()));
//			upavrage.setText(((Student) node.getData()).getAverage() + "");

		});

		upback.setOnAction(e -> {
			stage.setScene(homeScene);
			stage.show();
		});

		upBT.setOnAction(e -> {
			try {
				if ((Double.parseDouble(upavrage.getText()) >= 100.0) || (upavrage.getText().equals(null))) {
					Alert alert = new Alert(Alert.AlertType.NONE, "not corect gread value", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
					return;
				} else {

					DNode node = dl.searchedID(Integer.parseInt(upNumber.getText()));
					Student student = (Student) node.getData();
					TNode gradeNode = grade.FindNode(student.getAverage());
					if (gradeNode != null) {
						gradeNode.linkedList.delete(student);

						if (gradeNode.linkedList.getHead() == null) {
							grade.delete((Double) gradeNode.getData());
						}
					}
					student.setAverage(Double.parseDouble(upavrage.getText()));
					TNode newGradeNode = grade.FindNode(student.getAverage());
					if (newGradeNode == null) {
						grade.insert(student.getAverage());
						newGradeNode = grade.FindNode(student.getAverage());
					}
					newGradeNode.linkedList.insertLast(student);
					TNode seatNumberNode = id.FindNode1(student.getSeatNumber());
					node.curr = seatNumberNode;
					node.curr1 = newGradeNode;
					Alert alert = new Alert(Alert.AlertType.NONE, "The Student is Updated", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				}
			} catch (Exception t) {
				Alert alert = new Alert(Alert.AlertType.NONE, "not corect seatNumber value", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			}
		});
//|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
		GridPane deletePane = new GridPane();
		Scene deleteScene = new Scene(deletePane, 500, 500);
		TextField seatNumberDE = new TextField();
		deletePane.add(new Label("Seat Number"), 1, 1);
		deletePane.add(seatNumberDE, 3, 1);
		deletePane.setHgap(50);
		deletePane.setVgap(50);
		Button deleteBt = new Button("Delete XX");
		deletePane.add(deleteBt, 3, 3);
		Button backDelete = new Button("<-- Back");
		deletePane.add(backDelete, 3, 5);
		delet.setOnAction(e -> {
			stage.setScene(deleteScene);
			stage.show();
		});
		deleteBt.setOnAction(e -> {
			try {
				DNode node = dl.searchedID(Integer.parseInt(seatNumberDE.getText()));
				Student stud = (Student) node.getData();
				TNode node1 = id.FindNode1(Integer.parseInt(seatNumberDE.getText()));
				TNode node2 = grade.FindNode(stud.getAverage());

				if (node != null) {
					dl.deleteD(stud);
					id.delete(stud.getSeatNumber());
					if ((Double) node2.data == stud.getAverage()) {
						node2.linkedList.delete(stud);
					}
					if (node2.linkedList.getHead() == null)
						grade.delete(stud.getAverage());

					Alert alert = new Alert(Alert.AlertType.NONE, "Student is deleted", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}

				} else {
					Alert alert = new Alert(Alert.AlertType.NONE, "Student does not found", ButtonType.OK);
					if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
					}
				}
			} catch (Exception t) {
				Alert alert = new Alert(Alert.AlertType.NONE, "unvalid value in seat number ", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			}
		});
		backDelete.setOnAction(e -> {
			stage.setScene(homeScene);
			stage.show();
		});
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		GridPane findPane = new GridPane();
		Scene sceneFind = new Scene(findPane, 800, 550);
		TextField findTF = new TextField();
		findPane.add(new Label("Enter the seat number"), 1, 1);
		findPane.add(findTF, 2, 2);
		TextArea findTa = new TextArea();
		findPane.add(findTa, 2, 3);
		Button findBT = new Button("find");
		findPane.add(findBT, 1, 5);
		findPane.setHgap(40);
		findPane.setVgap(50);
		Button backFind = new Button("<--Back");
		findPane.add(backFind, 3, 5);
		find.setOnAction(e -> {
			stage.setScene(sceneFind);
			stage.show();
		});
		findBT.setOnAction(e -> {
			try {
				DNode node = dl.searchedID(Integer.parseInt(findTF.getText()));
				findTa.setText(node.getData().toString() + "\nprev\t" + node.prev.getData().toString() + "\nnext\t"
						+ node.next.getData().toString());
			} catch (Exception i) {
				Alert alert = new Alert(Alert.AlertType.NONE, "not corect seatNumber value", ButtonType.OK);
				if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
				}
			}

		});
		backFind.setOnAction(e -> {
			stage.setScene(homeScene);
			stage.show();
		});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		GridPane DListPane = new GridPane();
		Scene sceneDList = new Scene(DListPane, 800, 550);
		DListPane.add(new Label("print double linked list"), 1, 1);
		TextArea DListTa = new TextArea();
		DListPane.add(DListTa, 2, 2);
		Button DListBT = new Button("print Orgenal");
		DListPane.add(DListBT, 1, 3);
		Button DListBT1 = new Button("print 1'st AVL");
		DListPane.add(DListBT1, 1, 4);
		Button DListBT2 = new Button("print 2'st AVL");
		DListPane.add(DListBT2, 1, 5);
		Button clear = new Button("clear Text Area");
		DListPane.add(clear, 3, 3);
		DListPane.setHgap(10);
		DListPane.setVgap(20);
		Button backDList = new Button("<--Back");
		DListPane.add(backDList, 3, 4);
		DoubleLink.setOnAction(e -> {
			stage.setScene(sceneDList);
			stage.show();
		});
		DListBT.setOnAction(e -> {
			DListTa.setText(dl.print());
		});
		DListBT1.setOnAction(e -> {
//			1st AVL level Traversal
			DListTa.setText(id.traversIdAVL());
		});
		DListBT2.setOnAction(e -> {
//			2st AVL level Traversal 
			DListTa.setText(grade.traversGradeAVL());
		});
		clear.setOnAction(e -> {
			DListTa.setText(null);
		});

		backDList.setOnAction(e -> {
			stage.setScene(homeScene);
			stage.show();
		});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		GridPane heigthPane = new GridPane();
		Scene sceneheigth = new Scene(heigthPane, 800, 500);
		heigthPane.add(new Label("print AVL's Heigth"), 1, 1);
		TextArea heigthTa = new TextArea();
		heigthPane.add(heigthTa, 2, 2);
		Button heigthBT = new Button("print");
		heigthPane.add(heigthBT, 1, 3);
		heigthPane.setHgap(40);
		heigthPane.setVgap(50);
		Button backheigth = new Button("<--Back");
		heigthPane.add(backheigth, 3, 3);
		heigth.setOnAction(e -> {
			stage.setScene(sceneheigth);
			stage.show();
		});
		heigthBT.setOnAction(e -> {
			heigthTa.setText("first AVL height is : " + id.height() + "\n \nsecond AVL height is : " + grade.height());
		});
		backheigth.setOnAction(e -> {
			stage.setScene(homeScene);
			stage.show();
		});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		stage.setScene(ReadScene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
