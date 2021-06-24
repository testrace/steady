import React from 'react';
import { render, screen } from '@testing-library/react';
import Steady from "./Steady";


test('메인화면 테스트', () => {
  render(<Steady />);
  const linkElement = screen.getByText(/steady/i);
  expect(linkElement).toBeInTheDocument();



});

