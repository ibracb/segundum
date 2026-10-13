package segundum.domain.exceptions.user.rating;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when the score of a rating is not between 1 and 5.
 */
@SuppressWarnings("serial")
public class RatingScoreInvalidException extends DomainException {

	/**
	 * Constructs a new RatingScoreInvalidException with a message indicating
	 * that the score must be between the given bounds.
	 *
	 * @param score the invalid score
	 * @param minScore the minimum allowed score
	 * @param maxScore the maximum allowed score
	 */
	public RatingScoreInvalidException(int score, int minScore, int maxScore) {
		super("The score must be between " + minScore + " and " + maxScore + ", but it was " + score);
	}

}
