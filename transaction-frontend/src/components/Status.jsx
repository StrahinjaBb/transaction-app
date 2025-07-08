const Status = ({ status }) => {
  const getStatusColor = () => {
    switch (status.toLowerCase()) {
      case 'pending':
        return 'yellow';
      case 'settled':
        return 'green';
      case 'failed':
        return 'red';
      default:
        return 'grey';
    }
  };

  const statusText = {
    'pending': 'Pending',
    'settled': 'Completed',
    'failed': 'Failded'
  };

  return (
    <span className={`status-badge status-${getStatusColor()}`}>
      {statusText[status.toLowerCase()] || status}
    </span>
  );
};

export default Status;