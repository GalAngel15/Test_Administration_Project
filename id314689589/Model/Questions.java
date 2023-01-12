package Model;

import java.io.Serializable;

public abstract class Questions implements Comparable<Questions> , Serializable, Cloneable {
	private static int counter = 0;
	private int qId;
	private String description;

	public Questions(String description) {
		qId = ++counter;
		this.description = description;

	}

	public Questions(Questions other) {
		this.description=other.description;
		this.qId = other.qId;
	}
	public int getNumAnswers() {
		return 1;
	}

	public int getqId() {
		return qId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Answers getAnswer() {
		return null;
	}

	public abstract boolean addAnswer(Answers answer);

	public abstract boolean setAnswer(Answers answer, int index);
	
	public abstract int getAnswerLength();
	public abstract boolean addDefultAnswers();

	@Override
	public String toString() {
		return getDescription()+ "\n";
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if(getClass()!=obj.getClass())
			return false;
		Questions other = (Questions) obj;
		return (description.equalsIgnoreCase(other.getDescription()));
	}

	public int compareTo(Questions o) {
		return description.compareToIgnoreCase(o.description);
	}
	public String toStringQuestions() {
		return description+"\n";
	}
	public abstract Questions clone();

}
