import React, { useState } from 'react';
import './App.css';

function App() {
  const [startWord, setStartWord] = useState('');
  const [endWord, setEndWord] = useState('');
  const [wordLadder, setWordLadder] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleChangeStartWord = (e) => {
    setStartWord(e.target.value);
  };

  const handleChangeEndWord = (e) => {
    setEndWord(e.target.value);
  };

  const handleStartGame = async () => {
    setLoading(true);
    setError('');

    try {
      const response = await fetch(`/api/word-ladder?start=${startWord}&end=${endWord}`);
      const data = await response.json();
      setWordLadder(data.wordLadder);
    } catch (err) {
      setError('Failed to fetch word ladder. Please try again.');
    }

    setLoading(false);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Word Ladder Game</h1>
        <div className="form-group">
          <label htmlFor="startWord">Start Word:</label>
          <input
            type="text"
            id="startWord"
            value={startWord}
            onChange={handleChangeStartWord}
            disabled={loading}
          />
        </div>
        <div className="form-group">
          <label htmlFor="endWord">End Word:</label>
          <input
            type="text"
            id="endWord"
            value={endWord}
            onChange={handleChangeEndWord}
            disabled={loading}
          />
        </div>
        <button onClick={handleStartGame} disabled={loading}>
          {loading ? 'Loading...' : 'Start Game'}
        </button>
        {error && <p className="error">{error}</p>}
      </header>
      <main>
        {wordLadder.length > 0 && (
          <div className="word-ladder">
            <h2>Word Ladder:</h2>
            <ul>
              {wordLadder.map((word, index) => (
                <li key={index}>{word}</li>
              ))}
            </ul>
          </div>
        )}
      </main>
    </div>
  );
}

export default App;
