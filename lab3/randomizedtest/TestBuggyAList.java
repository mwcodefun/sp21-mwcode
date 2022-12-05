package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

  @Test
  public void testThreeAddThreeRemove(){
    AListNotResizing a = new AListNotResizing();
    BuggyAList b = new BuggyAList();
    for (int i = 0; i < 3; i++) {
      a.addLast(i);
      b.addLast(i);
    }
    for (int i = 0; i < 3; i++) {
      a.removeLast();
      b.removeLast();
    }
    assertEquals(a.size(), b.size());

  }

}
